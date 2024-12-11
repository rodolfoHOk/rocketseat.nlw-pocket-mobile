import { useEffect, useRef, useState } from 'react';
import { Alert, Modal, ScrollView, StatusBar, View } from 'react-native';
import { Redirect, router, useLocalSearchParams } from 'expo-router';
import { useCameraPermissions, CameraView } from 'expo-camera';
import { api } from '@/services/api';
import { MarketDetails } from '@/utils/models';
import { Loading } from '@/components/loading';
import { Cover } from '@/components/market/cover';
import { Details } from '@/components/market/details';
import { Coupon } from '@/components/market/coupon';
import { Button } from '@/components/button';

type DataProps = MarketDetails & {
  cover: string;
};

export default function Marker() {
  const params = useLocalSearchParams<{ id: string }>();

  const [_, requestPermission] = useCameraPermissions();

  const [isLoading, setIsLoading] = useState(true);
  const [data, setData] = useState<DataProps>();
  const [coupon, setCoupon] = useState<string | null>(null);
  const [couponIsFetching, setCouponIsFetching] = useState(false);
  const [isVisibleCameraModal, setIsVisibleCameraModal] = useState(false);

  const qrLock = useRef(false);

  async function fetchMarket() {
    try {
      const { data } = await api.get(`/markets/${params.id}`);
      setData(data);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
      Alert.alert('Erro', 'Não foi possível carregar os dados', [
        {
          text: 'OK',
          onPress: () => router.back(),
        },
      ]);
    }
  }

  async function getCoupon(id: string) {
    try {
      setCouponIsFetching(true);

      const { data } = await api.patch('/coupons/' + id);

      Alert.alert('Cupom', data.coupon);

      setCoupon(data.coupon);
    } catch (error) {
      console.log(error);
      Alert.alert('Erro', 'Não foi possível utilizar o cupom');
    } finally {
      setCouponIsFetching(false);
    }
  }

  async function handleOpenCamera() {
    try {
      const { granted } = await requestPermission();

      if (!granted) {
        return Alert.alert('Câmera', 'Você precisa habilitar o uso da câmera');
      }

      qrLock.current = false;
      setIsVisibleCameraModal(true);
    } catch (error) {
      console.log(error);
      Alert.alert('Câmera', 'Não foi possível utilizar a câmera');
    }
  }

  function handleUseCoupon(id: string) {
    setIsVisibleCameraModal(false);

    Alert.alert(
      `Cupom: ${id}`,
      'Não é possível reutilizar um cupom resgatado. Deseja realmente resgatar o cupom?',
      [
        { style: 'cancel', text: 'Não' },
        { text: 'Sim', onPress: () => getCoupon(id) },
      ]
    );
  }

  useEffect(() => {
    console.log(params.id);
    fetchMarket();
  }, [params.id, coupon]);

  if (isLoading) {
    return <Loading />;
  }

  if (!data) {
    return <Redirect href="/home" />;
  }

  return (
    <View style={{ flex: 1 }}>
      <StatusBar barStyle="light-content" hidden={isVisibleCameraModal} />

      <ScrollView showsVerticalScrollIndicator={false}>
        <Cover uri={data.cover} />

        <Details data={data} />

        {coupon && <Coupon code={coupon} />}
      </ScrollView>

      <View style={{ padding: 32 }}>
        <Button onPress={handleOpenCamera}>
          <Button.Title>Ler QR Code</Button.Title>
        </Button>
      </View>

      <Modal style={{ flex: 1 }} visible={isVisibleCameraModal}>
        <CameraView
          style={{ flex: 1 }}
          facing="back"
          onBarcodeScanned={({ data }) => {
            if (data && !qrLock.current) {
              qrLock.current = true;
              setTimeout(() => handleUseCoupon(data), 500);
            }
          }}
        />

        <View style={{ position: 'absolute', bottom: 32, left: 32, right: 32 }}>
          <Button
            onPress={() => setIsVisibleCameraModal(false)}
            isLoading={couponIsFetching}
          >
            <Button.Title>Voltar</Button.Title>
          </Button>
        </View>
      </Modal>
    </View>
  );
}

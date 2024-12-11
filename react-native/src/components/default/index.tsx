import { Text, View } from 'react-native';
import { s } from './styles';

export function DefaultComponent() {
  return (
    <View style={s.container}>
      <Text>ToDo</Text>
    </View>
  );
}

import { StyleSheet } from 'react-native';
import { colors, fontFamily } from '@/styles/theme';

export const s = StyleSheet.create({
  container: {
    flex: 1,
  },
  title: {
    fontFamily: fontFamily.regular,
    color: colors.gray[100],
  },
});

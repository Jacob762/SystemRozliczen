import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center } from 'styled-system/jsx';

export const metadata: Metadata = {
  title: 'Dashboard',
};

export default function Home() {
  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold' })}>
        {metadata.title as string}
      </h1>
      <Center className={css({ fontSize: '2xl', fontWeight: 'bold' })}>
        Hello 🐼!
      </Center>
    </>
  );
}

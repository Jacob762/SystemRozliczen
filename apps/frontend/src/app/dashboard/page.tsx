import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center, Flex, Stack } from 'styled-system/jsx';
import LatestDocuments from '~/components/dashboard/LatestDocuments';
import Statistics from '~/components/dashboard/Statistics';

export const metadata: Metadata = {
  title: 'Dashboard',
};

export default function Dashboard() {
  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold', py: '5' })}>
        {metadata.title as string}
      </h1>
      <Stack
        direction="column"
        className={css({ fontSize: '2xl', fontWeight: 'bold' })}
      >
        <Statistics />
        <LatestDocuments />
      </Stack>
    </>
  );
}

import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import { getDocuments } from '~/api/getDocuments';
import LatestDocuments from '~/components/dashboard/LatestDocuments';
import Statistics from '~/components/dashboard/Statistics';

export const metadata: Metadata = {
  title: 'Dashboard',
};

export default async function Dashboard() {
  const initialData = await getDocuments();

  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold', py: '5' })}>
        {metadata.title as string}
      </h1>
      <Stack
        direction="row"
        gap="5"
        className={css({ fontSize: '2xl', fontWeight: 'bold' })}
      >
        <Statistics />
        <LatestDocuments documents={initialData} />
      </Stack>
    </>
  );
}

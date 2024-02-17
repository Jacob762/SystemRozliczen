import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center } from 'styled-system/jsx';
import {getAccountant} from '~/api/getAccountant';
import { getOrganization } from '~/api/getOrganization';
import Dashboard from './dashboard';

export const metadata: Metadata = {
  title: 'Accountant',
};

export default async function Accountant() {
  const initialData = await getAccountant();
  const orgInitialData = await getOrganization();

  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold' })}>
        {metadata.title as string}
      </h1>
      <Center className={css({ fontSize: '2xl', fontWeight: 'bold' })}>
        Panel ksiegowego
      </Center>
      <Dashboard accountant={initialData} organization={orgInitialData}/>
    </>
  );
}

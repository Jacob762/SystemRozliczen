import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center, Stack } from 'styled-system/jsx';
import AccountantDashboard from '~/components/dashboard/Accountant/AccountantDashboard';
import AccountantDeleteDashboard from '~/components/dashboard/Accountant/AccountantDeleteDashboard';
import AccountantEditDocument from '~/components/dashboard/Accountant/AccountantEditDocument';

export const metadata: Metadata = {
  title: 'Accountant',
};

export default function Accountant() {
  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold' })}>
        {metadata.title as string}
      </h1>
      <Center className={css({ fontSize: '2xl', fontWeight: 'bold' })}>
        Panel ksiegowego
      </Center>
      <Stack direction="column" gap="4">
        <AccountantDashboard />
        <AccountantEditDocument />
        <AccountantDeleteDashboard />
      </Stack>
    </>
  );
}

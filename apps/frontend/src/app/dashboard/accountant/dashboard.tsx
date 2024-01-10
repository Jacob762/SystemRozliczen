'use client';

import { useQuery } from '@tanstack/react-query';
import { Stack } from 'styled-system/jsx';
import { getAccountant } from '~/api/getAccountant';
import AccountantDashboard from '~/components/dashboard/Accountant/AccountantDashboard';
import AccountantDeleteDashboard from '~/components/dashboard/Accountant/AccountantDeleteDashboard';
import AccountantEditDocument from '~/components/dashboard/Accountant/AccountantEditDocument';

export default function Dashboard(props: { accountant: any }) {
  const { data } = useQuery({
    queryKey: ['accountant'],
    queryFn: getAccountant,
    initialData: props.accountant,
  });

  return (
    <Stack direction="column" gap="4">
      <AccountantDashboard accountant={data} />
      <AccountantEditDocument />
      <AccountantDeleteDashboard />
    </Stack>
  );
}

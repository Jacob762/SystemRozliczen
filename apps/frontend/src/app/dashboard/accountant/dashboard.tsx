'use client';

import { useQuery } from '@tanstack/react-query';
import { Stack } from 'styled-system/jsx';
import { getAccountant } from '~/api/getAccountant';
import { getOrganization } from '~/api/getOrganization';
import AccountantDashboard from '~/components/dashboard/Accountant/AccountantDashboard';


export default function Dashboard(props: {
  accountant: any;
  organization: any;
}) {
  const { data } = useQuery({
    queryKey: ['accountant'],
    queryFn: getAccountant,
    initialData: props.accountant,
  });

  const { data: orgData } = useQuery({
    queryKey: ['organization'],
    queryFn: getOrganization,
    initialData: props.organization,
  });

  return (
    <Stack direction="column" gap="4">
      <AccountantDashboard accountant={data} organization={orgData} />
    </Stack>
  );
}

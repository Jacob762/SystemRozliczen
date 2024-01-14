'use client';

import { useQuery } from '@tanstack/react-query';
import { getDocuments } from '~/api/getDocuments';
import * as Table from '~/components/ui/table';
import Document from './document';

export default function Documents(props: { documents: any }) {
  const { data } = useQuery({
    queryKey: ['documents'],
    queryFn: getDocuments,
    initialData: props.documents,
  });

  return (
    <Table.Root>
      <Table.Head>
        <Table.Row>
          <Table.Header>ID</Table.Header>
          <Table.Header>Nazwa</Table.Header>
          <Table.Header>Data</Table.Header>
          <Table.Header>Kwota</Table.Header>
          <Table.Header>Księgowy</Table.Header>
          <Table.Header></Table.Header>
          <Table.Header></Table.Header>
        </Table.Row>
      </Table.Head>
      <Table.Body>
        {data.map((document: any) => (
          <Document key={document.id} document={document} />
        ))}
      </Table.Body>
    </Table.Root>
  );
}

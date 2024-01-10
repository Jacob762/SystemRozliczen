'use client';

import { useQuery } from '@tanstack/react-query';
import { getDocuments } from '~/api/getDocuments';
import * as Card from '~/components/ui/card';
import { Button } from '../ui/button';
import * as Table from '../ui/table';

export default function LatestDocuments(props: { documents: any }) {
  const { data } = useQuery({
    queryKey: ['documents'],
    queryFn: getDocuments,
    initialData: props.documents,
  });

  return (
    <Card.Root width="lg">
      <Card.Header>
        <Card.Title>Ostatnie dokumenty</Card.Title>
      </Card.Header>
      <Card.Body>
        <Table.Root>
          <Table.Head>
            <Table.Row>
              <Table.Header>ID</Table.Header>
              <Table.Header>Nazwa</Table.Header>
              <Table.Header>Data</Table.Header>
            </Table.Row>
          </Table.Head>
          <Table.Body>
            {data.slice(0, 5).map((document: any) => (
              <Table.Row key={document.id}>
                <Table.Cell fontWeight="medium">{document.id}</Table.Cell>
                <Table.Cell>{document.nazwa}</Table.Cell>
                <Table.Cell>
                  {new Date(document.data).toISOString().split('T')[0]}
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table.Root>
      </Card.Body>
      <Card.Footer>
        <Button asChild>
          <a href="/dashboard/documents">Zobacz więcej</a>
        </Button>
      </Card.Footer>
    </Card.Root>
  );
}

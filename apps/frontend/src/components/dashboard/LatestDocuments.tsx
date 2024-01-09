import * as Card from '~/components/ui/card';
import { Button } from '../ui/button';
import * as Table from '../ui/table';

export default function LatestDocuments() {
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
            {documentData.map((document, index) => (
              <Table.Row key={index}>
                <Table.Cell fontWeight="medium">{document.id}</Table.Cell>
                <Table.Cell>{document.name}</Table.Cell>
                <Table.Cell>{document.date}</Table.Cell>
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

const documentData = [
  { id: 'FVAT/01/1', name: 'System CRM', date: '2023/11/12' },
  { id: 'FVAT/01/2', name: 'Mapa cyfrowa', date: '2023/11/15' },
  { id: 'FVAT/01/3', name: 'Przepis z labów', date: '2023/11/17' },
];

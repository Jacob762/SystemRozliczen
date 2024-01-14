import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center, Stack } from 'styled-system/jsx';
import { getDocuments } from '~/api/getDocuments';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';
import Documents from './documents';

export const metadata: Metadata = {
  title: 'Dokumenty',
};

export default async function DocumentsPage() {
  const initialData = await getDocuments();

  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold', py: '5' })}>
        {metadata.title as string}
      </h1>
      <Center className={css({ fontSize: '2xl', fontWeight: 'bold' })}></Center>
      <Card.Root>
        <Card.Header>
          <Card.Title>Ostatnie dokumenty</Card.Title>
        </Card.Header>
        <Card.Body>
          <Stack>
            {/*/document/{idOrg} - getDokumenty, DocumentController*/}
            <Button alignSelf="center" width="xs">
              Sortuj po dacie
            </Button>
            <Documents documents={initialData} />
          </Stack>
        </Card.Body>
      </Card.Root>
    </>
  );
}

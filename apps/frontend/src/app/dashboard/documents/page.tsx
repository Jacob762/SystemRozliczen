import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center, Stack } from 'styled-system/jsx';
import { getDocuments } from '~/api/getDocuments';
import { Button } from '~/components/ui/button';
import Docs from './docs';

export const metadata: Metadata = {
  title: 'Dokumenty',
};

export default async function Documents() {
  const initialData = await getDocuments();

  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold' })}>
        {metadata.title as string}
      </h1>
      <Center className={css({ fontSize: '2xl', fontWeight: 'bold' })}></Center>
      <Stack>
        {/*/document/{idOrg} - getDokumenty*/}
        <Button>Sortuj po kwocie</Button>{' '}
        {/*/document/sort/{id} - idOrganizacji*/}
        <Docs documents={initialData} />
      </Stack>
    </>
  );
}

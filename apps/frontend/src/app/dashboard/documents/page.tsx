import { Metadata } from 'next';
import { css } from 'styled-system/css';
import { Center, Flex, Stack } from 'styled-system/jsx';
import Document from "~/components/dashboard/Document";
import {Button} from "~/components/ui/button";
export const metadata: Metadata = {
  title: 'Dokumenty',
};

export default function Documents() {
  return (
    <>
      <h1 className={css({ fontSize: '4xl', fontWeight: 'bold' })}>
        {metadata.title as string}
      </h1>
      <Center className={css({ fontSize: '2xl', fontWeight: 'bold'})}>
      </Center>
      <Stack> {/*/document/{idOrg} - getDokumenty*/}
          <Button>Sortuj po kwocie</Button> {/*/document/sort/{id} - idOrganizacji*/}
          <Document/>
          <Document/>
          <Document/>
      </Stack>
    </>
  );
}

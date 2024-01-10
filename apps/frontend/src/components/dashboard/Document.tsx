import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';

export default function Document(props: { document: any }) {
  const { document } = props;

  return (
    <Card.Root width="mg">
      <Card.Header>
        <Card.Title>Dokument nr {document.id}</Card.Title>{' '}
        <Card.Description>{document.nazwa}</Card.Description>
      </Card.Header>
      <Card.Body>
        <Stack direction="row" gap="4">
          <div className={css({ flex: '1' })}>
            <Card.Title>Kwota</Card.Title>
            <Card.Description>{document.kwota}</Card.Description>
          </div>
          <div className={css({ flex: '1' })}>
            <Card.Title>Księgowy</Card.Title>
            <Card.Description>{document.autor.nazwa}</Card.Description>
          </div>
          <Stack direction="column" gap="2">
            <div className={css({ flex: '1' })}>
              <Button asChild>
                <a>Usuń dokument</a>
              </Button> {/* todo DELETE :   /document/{idOrg}/{id}   gdzie idOrg - id organizacji, id - id dokumentu, DocumentController*/}
            </div>
            <div className={css({ flex: '1' })}>
              <Button asChild>
                <a>Edytuj dokument</a>
              </Button>{/* todo POST :   /document/edit - tu wymagany string z json, przekazywane dalej do funkcji dodaj_dokument, DocumentController*/}
            </div>
          </Stack>
        </Stack>
      </Card.Body>
    </Card.Root>
  );
}

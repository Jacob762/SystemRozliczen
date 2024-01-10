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
          <div className={css({ flex: '1' })}>
            <Button asChild>
              <a>Pobierz plik</a>
            </Button>{' '}
            {/* todo - przycisk do pobierania plikow*/}
          </div>
        </Stack>
      </Card.Body>
    </Card.Root>
  );
}

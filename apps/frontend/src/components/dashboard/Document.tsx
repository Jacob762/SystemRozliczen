import { css } from '../../../styled-system/css';
import { Stack } from '../../../styled-system/jsx';
import * as Card from '~/components/ui/card';
import { red } from 'next/dist/lib/picocolors';
import { Button } from '~/components/ui/button';

export default function Document() {
  return (
    <Card.Root width="mg">
      <Card.Header>
        <Card.Title>Dokument nr {'x'}</Card.Title>{' '}
        {/* jako x dodajemy id dokumentu*/}
        <Card.Description>Nazwa dokumentu</Card.Description>
      </Card.Header>
      <Card.Body>
        <Stack direction="row" gap="4">
          <div className={css({ flex: '1' })}>
            <Card.Title>Kwota</Card.Title>
            <Card.Description>26.99</Card.Description>
          </div>
          <div className={css({ flex: '1' })}>
            <Card.Title>Ksiegowy</Card.Title>
            <Card.Description>Grzegorz Malecki</Card.Description>
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

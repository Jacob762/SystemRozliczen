import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import * as Card from '~/components/ui/card';
import {Button} from "~/components/ui/button";

export default function Statistics() {
  return (
    <Card.Root width='lg'>
      <Card.Header>
        <Card.Title>Statystyki</Card.Title>
        <Card.Description>
            <Button>Ostatnie 30 dni</Button> {/*/statystyka  - string jako wejsciowe, zobacz w controllerze*/}
        </Card.Description>
      </Card.Header>
      <Card.Body>
          <Stack direction="column" gap="3">
              <Stack direction="row" gap="4">
                  <div className={css({ flex: '1' })}>
                      <Card.Title>Liczba dokumentow</Card.Title>
                      <Card.Description>123</Card.Description>
                  </div> {/*/document/liczba/{idOrg}*/}
                  <div className={css({ flex: '1' })}>
                      <Card.Title>Użytkownicy</Card.Title>
                      <Card.Description>123</Card.Description>
                  </div>
              </Stack>
              <Stack direction="row" gap="4">
                  <div className={css({ flex: '1' })}>
                      <Card.Title>Ksiegowi</Card.Title>
                      <Card.Description>123</Card.Description>
                  </div>
                  <div className={css({ flex: '1' })}>
                      <Card.Title>Laczna kwota</Card.Title>
                      <Card.Description>123</Card.Description>
                  </div> {/*statystyka/total statystyka*/}
              </Stack>
          </Stack>
      </Card.Body>
    </Card.Root>
  );
}

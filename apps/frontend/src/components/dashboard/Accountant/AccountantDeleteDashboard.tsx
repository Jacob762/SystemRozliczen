import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';

export default function AccountantDeleteDashboard() {
  return (
    <Card.Root width="lg">
      <Card.Header>
        <Card.Title>Usun dokument</Card.Title>
        <Card.Description>Input id</Card.Description>
      </Card.Header>
      <Card.Body>
        <Stack direction="row" gap="5">
          <div className={css({ flex: '1' })}>
            <Button asChild>
              <a>Usun dokument</a>
            </Button>{' '}
            {/*/document/{idOrg}/{id}*/}
          </div>
        </Stack>
      </Card.Body>
    </Card.Root>
  );
}

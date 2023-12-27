import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import * as Card from '~/components/ui/card';

export default function Statistics() {
  return (
    <Card.Root width='lg'>
      <Card.Header>
        <Card.Title>Statystyki</Card.Title>
        <Card.Description>ostatnie 30 dni</Card.Description>
      </Card.Header>
      <Card.Body>
        <Stack direction="row" gap="4">
          <div className={css({ flex: '1' })}>
            <Card.Title>Wizyty</Card.Title>
            <Card.Description>123</Card.Description>
          </div>
          <div className={css({ flex: '1' })}>
            <Card.Title>Użytkownicy</Card.Title>
            <Card.Description>123</Card.Description>
          </div>
        </Stack>
      </Card.Body>
      <Card.Footer>
        <Card.Title>Wizyty</Card.Title>
        <Card.Description>123</Card.Description>
      </Card.Footer>
    </Card.Root>
  );
}

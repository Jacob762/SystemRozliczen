'use client';

import { useQuery } from '@tanstack/react-query';
import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import { getStatistics } from '~/api/getStatistics';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';

export default function Statistics(props: { statistics: any }) {
  const { data } = useQuery({
    queryKey: ['statistics'],
    queryFn: getStatistics,
    initialData: props.statistics,
  });

  return (
    <Card.Root width="lg">
      <Card.Header>
        <Card.Title>Statystyki</Card.Title>
        <Card.Description>
          <Button>Ostatnie 30 dni</Button>{' '}
          {/*/statystyka  - string jako wejsciowe, zobacz w controllerze*/}
        </Card.Description>
      </Card.Header>
      <Card.Body>
        <Stack direction="column" gap="3">
          <Stack direction="row" gap="4">
            <div className={css({ flex: '1' })}>
              <Card.Title>Liczba dokumentow</Card.Title>
              <Card.Description>{data.dokumenty}</Card.Description>
            </div>
            <div className={css({ flex: '1' })}>
              <Card.Title>Użytkownicy</Card.Title>
              <Card.Description>{data.pracownicy}</Card.Description>
            </div>
          </Stack>
          <Stack direction="row" gap="4">
            <div className={css({ flex: '1' })}>
              <Card.Title>Ksiegowi</Card.Title>
              <Card.Description>{data.ksiegowi}</Card.Description>
            </div>
            <div className={css({ flex: '1' })}>
              <Card.Title>Laczna kwota</Card.Title>
              <Card.Description>
                {Math.round((data.kwota + Number.EPSILON) * 100) / 100} zł
              </Card.Description>
            </div>
          </Stack>
        </Stack>
      </Card.Body>
    </Card.Root>
  );
}

import { ArrowRightIcon } from 'lucide-react';
import { Metadata } from 'next';
import Image from 'next/image';
import Link from 'next/link';
import { css } from 'styled-system/css';
import { center, flex } from 'styled-system/patterns';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';

export const metadata: Metadata = {
  title: 'EasyCRM',
};

export default function Home() {
  return (
    <main
      className={center({
        height: '100%',
      })}
    >
      <Card.Root
        className={css({
          maxWidth: '2xl',
        })}
      >
        <Card.Header>
          <Card.Title className={css({ fontSize: '2xl' })}>
            Witaj w EasyCRM
          </Card.Title>
          <Card.Description>Prosty w obsłudze System CRM</Card.Description>
        </Card.Header>
        <Card.Body className={css({ mt: '2' })}>
          EasyCRM to intuicyjny system do zarządzania relacjami z klientami,
          stworzony z myślą o łatwości obsługi. Oferuje efektywne narzędzia do
          śledzenia kontaktów, zarządzania sprzedażą i analizowania danych
          klientów. Zyskaj kontrolę nad swoim biznesem dzięki EasyCRM!
        </Card.Body>
        <Card.Footer className={flex({ justifyContent: 'center' })}>
          <Link href="/dashboard">
            <Button>
              Wejdź
              <ArrowRightIcon />
            </Button>
          </Link>
        </Card.Footer>
      </Card.Root>
    </main>
  );
}

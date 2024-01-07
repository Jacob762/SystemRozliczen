import { css } from '../../../../styled-system/css';
import { Stack } from '../../../../styled-system/jsx';
import * as Card from '~/components/ui/card';
import {red} from "next/dist/lib/picocolors";
import {Button} from "~/components/ui/button";

export default function AccountantDashboard() {
    return (
        <Card.Root width='lg'>
            <Card.Header>
                <Card.Title>Witaj {"nazwa ksiegowego"}</Card.Title> {/* jako x dodajemy id dokumentu*/}
                <Card.Description>{"id"}</Card.Description>
            </Card.Header>
            <Card.Body>
                <Stack direction="row" gap="4">
                    <div className={css({ flex: '2' })}>
                        <Card.Title>Liczba dodanych dokumentow</Card.Title>
                        <Card.Description>120</Card.Description>
                    </div> {/* todo wyszukanie liczby dokumentow w backendzie */}
                    <div className={css({ flex: '1' })}>
                        <Card.Title>Organizacja</Card.Title>
                        <Card.Description>Fundacja x</Card.Description>
                    </div>
                    <div className={css({ flex: '1' })}>
                        <Button asChild>
                            <a >Dodaj dokument</a>
                        </Button> {/* todo - przycisk do dodawania dokumentow*/}
                    </div>
                </Stack>
            </Card.Body>
        </Card.Root>
    );
}

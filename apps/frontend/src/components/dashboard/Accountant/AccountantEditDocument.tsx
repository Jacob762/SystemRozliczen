import { css } from '../../../../styled-system/css';
import { Stack } from '../../../../styled-system/jsx';
import * as Card from '~/components/ui/card';
import {red} from "next/dist/lib/picocolors";
import {Button} from "~/components/ui/button";

export default function AccountantEditDocument() {
    return (
        <Card.Root width='lg'>
            <Card.Header>
                <Card.Title>Edytuj dokument</Card.Title>
                <Card.Description>Input id</Card.Description>
            </Card.Header>
            <Card.Body>
                <Stack direction="row" gap="5">
                    <div className={css({ flex: '2' })}>
                        <Card.Title>Nowa nazwa</Card.Title>
                        {/*Input id dokumnetu*/}
                    </div> {/* todo wyszukanie liczby dokumentow w backendzie */}
                    <div className={css({ flex: '2' })}>
                        <Card.Title>Nowa kwota</Card.Title>
                        {/*Input*/}
                    </div>
                    <div className={css({ flex: '1' })}>
                        <Button asChild>
                            <a >Edytuj dokument</a>
                        </Button> {/*/document/edit*/}
                    </div>
                </Stack>
            </Card.Body>
        </Card.Root>
    );
}

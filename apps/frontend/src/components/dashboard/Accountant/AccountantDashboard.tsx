import { css } from 'styled-system/css';
import { Stack } from 'styled-system/jsx';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {addDocument, deleteDocument} from "~/api/getDocuments";

export default function AccountantDashboard(props: {
  accountant: any;
  organization: any;
}) {

  const queryClient = useQueryClient();

  const addMutation = useMutation({
    mutationFn: addDocument,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['documents'] });
    },
  });

  const { accountant, organization} = props;

  return (
    <Card.Root width="lg">
      <Card.Header>
        <Card.Title>Witaj {accountant.nazwa}</Card.Title>{' '}
        {/* jako x dodajemy id dokumentu*/}
        <Card.Description>ID : {accountant.id}</Card.Description>
      </Card.Header>
      <Card.Body>
        <Stack direction="row" gap="4">
          <div className={css({ flex: '2' })}>
            <Card.Title>Liczba dodanych dokumentow</Card.Title>
            <Card.Description>{accountant.documentsNumber}</Card.Description>
          </div>{' '}
          {/* todo wyszukanie liczby dokumentow w backendzie */}
          <div className={css({ flex: '1' })}>
            <Card.Title>Organizacja</Card.Title>
            <Card.Description>{organization.nazwa}</Card.Description>
          </div>
          <div className={css({ flex: '1' })}>
            <Button asChild>
              <a>Dodaj dokument</a>
            </Button>{' '}
            {/* todo - przycisk do dodawania dokumentow, DocumentController dodajDokument(String object)*/}
          </div>
        </Stack>
      </Card.Body>
    </Card.Root>
  );
}

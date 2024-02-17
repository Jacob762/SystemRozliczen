import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Ban, Edit, FileX2, Save } from 'lucide-react';
import { useState } from 'react';
import { Stack } from 'styled-system/jsx';
import { deleteDocument, editDocument } from '~/api/getDocuments';
import { IconButton } from '~/components/ui/icon-button';
import { Input } from '~/components/ui/input';
import * as Table from '~/components/ui/table';

export default function Document(props: { document: any }) {
  const { document } = props;

  const queryClient = useQueryClient();

  const editMutation = useMutation({
    mutationFn: editDocument,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['documents'] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: deleteDocument,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['documents'] });
    },
  });

  const [editMode, setEditMode] = useState(false);
  const [editableRow, setEditableRow] = useState({
    nazwa: document.nazwa,
    kwota: document.kwota,
  });

  const handleEditClick = () => {
    setEditMode(true);
  };

  const handleSaveClick = () => {
    setEditMode(false);

    editMutation.mutate({
      organizationId: 0,
      documentId: document.id,
      accountantId: 0,
      nazwa: editableRow.nazwa,
      kwota: parseFloat(editableRow.kwota),
    });
  };

  const handleCancelClick = () => {
    setEditMode(false);
  };

  const handleDeleteClick = () => {
    deleteMutation.mutate({
      organizationId: 0,
      documentId: document.id,
    });
  };

  const handleInputChange = (field: any, value: string | number) => {
    setEditableRow((prevRow: any) => ({ ...prevRow, [field]: value }));
  };

  return (
    <Table.Row key={document.id}>
      <Table.Cell fontWeight="medium">{document.id}</Table.Cell>
      <Table.Cell minW="64">
        {editMode ? (
          <Input
            minW="0"
            maxW="52"
            type="text"
            value={editableRow.nazwa}
            onChange={(e) => handleInputChange('nazwa', e.target.value)}
          />
        ) : (
          document.nazwa
        )}
      </Table.Cell>
      <Table.Cell>
        {new Date(document.data).toISOString().split('T')[0]}
      </Table.Cell>
      <Table.Cell>
        {editMode ? (
          <Input
            minW="0"
            maxW="52"
            type="text"
            value={editableRow.kwota}
            onChange={(e) => handleInputChange('kwota', e.target.value)}
          />
        ) : (
          document.kwota
        )}
      </Table.Cell>
      <Table.Cell>{document.autor.nazwa}</Table.Cell>
      <Table.Cell>
        {editMode ? (
          <Stack direction="row" gap="4">
            <IconButton onClick={() => handleSaveClick()}>
              <Save />
            </IconButton>
            <IconButton onClick={() => handleCancelClick()}>
              <Ban />
            </IconButton>
          </Stack>
        ) : (
          <IconButton onClick={() => handleEditClick()}>
            <Edit />
          </IconButton>
        )}
      </Table.Cell>
      <Table.Cell>
        <IconButton onClick={() => handleDeleteClick()}>
          <FileX2 />
        </IconButton>
      </Table.Cell>
    </Table.Row>
  );
}

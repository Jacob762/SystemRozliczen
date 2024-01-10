'use client';

import { useQuery } from '@tanstack/react-query';
import Document from '~/components/dashboard/Document';
import { getDocuments } from './getDocuments';

export default function Docs(props: { documents: any }) {
  const { data } = useQuery({
    queryKey: ['documents'],
    queryFn: getDocuments,
    initialData: props.documents,
  });

  return (
    <>
      {data.map((document: any) => (
        <Document key={document.id} document={document} />
      ))}
    </>
  );
}

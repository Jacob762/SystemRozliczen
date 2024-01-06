import type { Metadata } from 'next';
import { flex } from 'styled-system/patterns';
import Sidebar from '~/components/navigation/Sidebar';

export const metadata: Metadata = {
  title: 'Dashboard',
  description: 'Najlepszy system księgowy na świecie',
};

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Sidebar />
      <main
        className={flex({
          flexDir: 'column',
          height: '100%',
          alignItems: 'center',
          ps: '64',
        })}
      >
        {children}
      </main>
    </>
  );
}

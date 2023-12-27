import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import Sidebar from '~/components/navigation/Sidebar';
import './globals.css';
import { css } from 'styled-system/css';
import { center } from 'styled-system/patterns';

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'Dashboard',
  description: 'Najlepszy system księgowy na świecie',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className={css({ width: '100%', height: '100vh' })}>
          <Sidebar />
          <main
            className={css({
              bgColor: 'bg.emphasized',
              display: 'flex',
              flexDir: 'column',
              height: '100%',
              alignItems: 'center',
              ps: '64',
            })}
          >
            {children}
          </main>
        </div>
      </body>
    </html>
  );
}

'use client';

import { Files, Home, Shield } from 'lucide-react';
import NextLink from 'next/link';
import { stack } from 'styled-system/patterns';
import { Link } from '../ui/link';
import { usePathname } from 'next/navigation';
import { css } from 'styled-system/css';

const links = [
  { name: 'Dashboard', href: '/dashboard', icon: Home },
  {
    name: 'Dokumenty',
    href: '/dashboard/documents',
    icon: Files,
  },
  {
    name: 'Panel administracyjny',
    href: '/dashboard/admin',
    icon: Shield,
  },
];

export default function Links() {
  const pathname = usePathname();

  return (
    <ul className={stack()}>
      {links.map((link) => (
        <li key={link.name}>
          <Link
            asChild
            className={css({
              color: pathname === link.href ? 'accent.default' : 'fg.default',
            })}
          >
            <NextLink href={link.href}>
              <link.icon size={24} />
              {link.name}
            </NextLink>
          </Link>
        </li>
      ))}
    </ul>
  );
}

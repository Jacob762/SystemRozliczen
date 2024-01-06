import Image from 'next/image';
import { css } from 'styled-system/css';
import { Divider } from 'styled-system/jsx';
import Links from './Links';

export default function Sidebar() {
  return (
    <header
      className={css({
        bgColor: 'bg.default',
        borderColor: 'border.default',
        borderWidth: '1px',
        bottom: '0',
        display: 'flex',
        flexDir: 'column',
        height: '100%',
        left: '0',
        overflowX: 'hidden',
        position: 'fixed',
        px: '6',
        py: '8',
        shadow: 'sm',
        top: '0',
        width: '64',
      })}
    >
      <div>
        <Image src="/vercel.svg" alt="logo" width={200} height={200} />
        <Divider className={css({ my: '10' })} />
      </div>
      <nav>
        <Links />
      </nav>
    </header>
  );
}

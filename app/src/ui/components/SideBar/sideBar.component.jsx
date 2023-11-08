import { useState } from 'react';
import './sideBar.style.css';

export function SideBar({
  children,
  titulo,
  followScroll,
  hamburguerFollowScroll,
}) {
  const [active, setMode] = useState(true);
  const toggleMode = () => {
    setMode(!active);
  };

  return (
    <div>
      <div className={hamburguerFollowScroll}>
        <div className={active ? 'icon iconActive' : 'icon'}>
          <div
            className={
              active ? 'hamburguerWrapperOpened' : 'hamburguerWrapperClosed'
            }
            onClick={toggleMode}
          >
            <div className="hamburguer hamburguerIcon"></div>
          </div>
        </div>
      </div>
      <div className={followScroll}>
        <div className={active ? 'menu menuOpen' : 'menu menuClose'}>
          <div className="list">
            <h1>{titulo}</h1>
            <ul className="listItems">{children}</ul>
          </div>
        </div>
      </div>
    </div>
  );
}

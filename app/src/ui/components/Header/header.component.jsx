import { Link } from 'react-router-dom';
import avatarImage from '../../../assets/default_avatar.jpg';
import useGlobalUser from '../../../context/user.context';
import { useLogout } from '../../../hook/useLogout/useLogout.hook';
import './header.style.css';

export function Header({ headerClass }) {
  const { handleLogout } = useLogout();
  const [user] = useGlobalUser();

  return (
    <header className={`header ${headerClass}`}>
      <div className="header-container">
        <label>Tasktrackr</label>
        <Link to="/" className="link-transition">
          Início
        </Link>
        <div className="avatar-logout-box">
          {user.foto ? (
            <img className="avatar-img" src={user.foto} alt="foto de usuário" />
          ) : (
            <img
              className="avatar-img"
              src={avatarImage}
              alt="foto de usuário"
            />
          )}
          <nav className="drop-down-menu">
            <ul className="drop-down-menu-list">
              <li className="drop-down-menu-item">
                <Link to="/profile" className="link-transition">
                  <p>Meu perfil</p>
                </Link>
              </li>
              <li className="drop-down-menu-item">
                <Link className="link-transition" onClick={handleLogout}>
                  <p>Sair</p>
                </Link>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
  );
}

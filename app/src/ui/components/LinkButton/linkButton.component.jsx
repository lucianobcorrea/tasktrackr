import './linkButton.style.css';

export function LinkButton({ link, linkButtonName, action }) {
  return (
    <a className="link-button-style" href={link} onClick={action}>
      {linkButtonName}
    </a>
  );
}

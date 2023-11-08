import './footer.style.css';

export function Footer({ className }) {
  return (
    <>
      <footer className={`footer ${className}`}>
        <p>Tasktrackr 2023 ®</p>
      </footer>
    </>
  );
}

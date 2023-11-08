import './passwordInput.style.css';
export function PasswordInput({
  labelText,
  inputName,
  inputValue,
  onChange,
  forPassword,
}) {
  return (
    <>
      <label className="passwordInput-label" htmlFor={forPassword}>
        {labelText}
      </label>
      <input
        type="password"
        className="input-password"
        id={forPassword}
        name={inputName}
        autoComplete="off"
        value={inputValue}
        onChange={onChange}
      />
    </>
  );
}

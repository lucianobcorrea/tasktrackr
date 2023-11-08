import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useChangePass } from '../../../hook/useChangePass/useChangePass.hook';
import { useIsValidToken } from '../../../hook/useIsValidToken/useIsValidToken.hook';
import { Button } from '../../components/Button/button.component';
import { FormContainer } from '../../components/FormContainer/formContainer.component';
import { PasswordInput } from '../../components/PasswordInput/passwordInput.component';

export function ChangePassScreen() {
  const { verifyToken } = useIsValidToken();
  const { id, token } = useParams();
  const { input, handleRegisterChange, onChangePassClick } = useChangePass();

  useEffect(() => {
    verifyToken(id, token);
  }, []);

  return (
    <>
      <FormContainer>
        <PasswordInput
          labelText="Nova senha"
          inputName="senha"
          inputType="senha"
          forPassword="senha"
          inputValue={input.senha}
          onChange={handleRegisterChange}
        />
        <Button onClick={() => onChangePassClick(id, token)}>
          Alterar senha
        </Button>
      </FormContainer>
    </>
  );
}

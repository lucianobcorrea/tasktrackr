import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import useGlobalUser from '../../../context/user.context';
import {
  useForgotPassForm,
  useFormInputs,
  useRegisterFormInputs,
} from '../../../hook/index';
import {
  Button,
  FormContainer,
  LinkButton,
  Modal,
  PasswordInput,
  TextInput,
} from '../../index';
import './loginScreen.style.css';

export function Login() {
  const { formInputs, handleChange, handleSubmit } = useFormInputs();
  const [registerModal, setRegisterModal] = useState(false);
  const [forgotPass, setForgotPass] = useState(false);
  const { registerInputs, handleRegisterChange, handleRegisterSubmit } =
    useRegisterFormInputs();
  const { emailInput, handleEmailChange, handleSendEmailSubmit } =
    useForgotPassForm();
  const [user] = useGlobalUser();
  const navigate = useNavigate();

  const toggleModal = () => {
    setRegisterModal(!registerModal);
  };

  const toggleModalForgotPass = () => {
    setForgotPass(!forgotPass);
  };

  useEffect(() => {
    if (user) {
      navigate('/home');
    }
  }, [user, navigate]);

  return (
    <>
      <form onSubmit={handleSubmit} className="login-screen">
        <FormContainer>
          <TextInput
            placeholder="Ex.: luciano@gmail.com.br"
            labelText="Usuário"
            inputName="username"
            inputType="email"
            forId="username"
            inputValue={formInputs.username}
            onChange={handleChange}
          />
          <div className="button-space">
            <PasswordInput
              labelText="Senha"
              inputName="password"
              forId="password"
              forPassword="password"
              inputValue={formInputs.password}
              onChange={handleChange}
            />
            <Button>Entrar</Button>
          </div>

          <LinkButton
            link="#"
            linkButtonName="Criar nova conta"
            action={toggleModal}
          />
          <LinkButton
            link="#"
            linkButtonName="Esqueceu sua senha?"
            action={toggleModalForgotPass}
          />
        </FormContainer>
      </form>

      <form onSubmit={handleRegisterSubmit}>
        {registerModal && (
          <Modal toggleModal={toggleModal}>
            <h1>Cadastre-se</h1>
            <p>Campos com asterisco* são campos obrigatórios</p>
            <TextInput
              placeholder="Ex.: Beatriz"
              labelText="Nome*"
              inputName="nome"
              forId="nome"
              inputValue={registerInputs.nome}
              onChange={handleRegisterChange}
            />

            <PasswordInput
              labelText="Senha*"
              inputName="senha"
              forPassword="senha"
              inputValue={registerInputs.senha}
              onChange={handleRegisterChange}
            />

            <TextInput
              placeholder="Ex.: Beatriz@cwi.com.br"
              labelText="Email*"
              inputName="email"
              inputType="email"
              forId="email"
              inputValue={registerInputs.email}
              onChange={handleRegisterChange}
            />

            <TextInput
              placeholder="Ex.: (51)12345-6789"
              labelText="Telefone*"
              inputName="telefone"
              forId="telefone"
              inputValue={registerInputs.telefone}
              onChange={handleRegisterChange}
            />

            <TextInput
              placeholder="Cole o url de uma imagem aqui"
              labelText="Imagem de perfil"
              inputName="foto"
              forId="foto"
              inputValue={registerInputs.foto}
              onChange={handleRegisterChange}
            />

            <Button>Cadastre-se</Button>
          </Modal>
        )}
      </form>

      <form onSubmit={handleSendEmailSubmit}>
        {forgotPass && (
          <Modal toggleModal={toggleModalForgotPass}>
            <h1>Recuperação de senha</h1>
            <p>Digite seu email cadastrado.</p>

            <TextInput
              placeholder="Ex.: email@provedor.com"
              labelText="Email"
              inputName="email"
              inputType="email"
              forId="email"
              inputValue={emailInput.email}
              onChange={handleEmailChange}
            />

            <Button>Enviar</Button>
          </Modal>
        )}
      </form>
    </>
  );
}

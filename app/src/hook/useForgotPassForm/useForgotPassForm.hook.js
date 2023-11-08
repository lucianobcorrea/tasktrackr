import { useState } from 'react';
import { sendEmail } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useForgotPassForm() {
  const [emailInput, setEmailInput] = useState({ email: '' });
  const addToast = useToastContext();

  async function handleSendEmailSubmit(event) {
    event.preventDefault();

    try {
      if (emailInput.email === '') {
        addToast('Email precisa ser preenchido');
      } else {
        await sendEmail(emailInput);
        setTimeout(() => {
          window.location.reload(true);
        }, 1500);
        addToast('Verifique o seu email cadastrado!');
      }
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleEmailChange(event) {
    const { name, value } = event.target;

    setEmailInput((oldFormInputs) => ({
      ...oldFormInputs,
      [name]: value,
    }));
  }

  return {
    emailInput,
    handleEmailChange,
    handleSendEmailSubmit,
  };
}

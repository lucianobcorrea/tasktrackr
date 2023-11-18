import { useState } from 'react';
import { BsCalendar } from 'react-icons/bs';
import { CiCircleCheck } from 'react-icons/ci';
import { RiErrorWarningLine } from 'react-icons/ri';
import { isDateExpired, renderDate } from '../../../helpers/dateFunctions';
import { capitalize } from '../../../helpers/stringFunctions';
import {
  useCompleteTask,
  useDeleteTask,
  useUpdateTask,
} from '../../../hook/index';
import { ConfirmDeleteButton } from '../ConfirmButton/confirmDeleteButton.component';
import { Modal } from '../Modal/modal.component';
import { Tooltip } from '../Tooltip/tooltip.component';
import './taskCard.style.css';

export function TaskCard({ data, updateInfo }) {
  const { id, title, description, createdAt, deadlineDate, priority, status } =
    data;
  const [isModalVisible, setModal] = useState(false);
  const [updateTask, setUpdateTask] = useState(false);
  const { update, input, handleChange } = useUpdateTask(data);
  const { complete } = useCompleteTask();
  const { remove } = useDeleteTask();

  async function handleComplete(event) {
    event.stopPropagation();
    document.getElementById(id).className = 'task-card completed-card-animated';
    await complete(id);
    updateInfo();
  }

  async function handleRemove() {
    await remove(id, title);
    toggleModal();
    updateInfo();
  }

  async function handleSubmit(event) {
    event.preventDefault();
    await update();
    updateInfo();
    toggleUpdateTask();
  }

  function toggleModal() {
    setUpdateTask(false);
    setModal(!isModalVisible);
  }

  function toggleUpdateTask() {
    setUpdateTask(!updateTask);
  }

  function CheckButton({ status, handleComplete }) {
    return (
      <>
        {status === 'COMPLETED' ? null : (
          <button onClick={handleComplete} className="task-card-button">
            <CiCircleCheck className="task-card-icon" />
          </button>
        )}
      </>
    );
  }

  function UpdateTaskAdditionalInfo({ title, info, infoStyle }) {
    return (
      <div className="task-detail-additional-info">
        <p className="task-detail-additional-info-title">{title}</p>
        <p className={`task-detail-additional-information ${infoStyle}`}>
          {info}
        </p>
      </div>
    );
  }

  function TaskPriority({ priority }) {
    return (
      <Tooltip hiddenText={(priority)}>
        <RiErrorWarningLine
          className={'task-priority-icon ' + getPriorityStyle(priority)}
        />
      </Tooltip>
    );
  }

  function getPriorityStyle(priority) {
    switch (priority) {
      case 'AVERAGE':
        return 'task-priority-medium';
      case 'LOW':
        return 'task-priority-low';
      case 'HIGH':
        return 'task-priority-high';
      default:
        return 'task-priority-low';
    }
  }

  return (
    <>
      <div id={id} onClick={toggleModal} className="task-card">
        <div className="task-card-title-box">
          <CheckButton status={status} handleComplete={handleComplete} />
          <p className="tarefa-task-title">{title}</p>
          <TaskPriority priority={priority} />
        </div>
        <div
          className={
            isDateExpired(deadlineDate) && status !== 'COMPLETED'
              ? 'task-card-infos-expired-date'
              : 'task-card-infos'
          }
        >
          <BsCalendar className="tarefa-task-calendar-icon" />
          <p className="task-card-date">{renderDate(deadlineDate)}</p>
        </div>
      </div>

      {isModalVisible && (
        <Modal toggleModal={toggleModal}>
          <div className="task-detail-div">
            {updateTask ? (
              <div className="task-toggle-update">
                <form onSubmit={handleSubmit}>
                  <div className="task-form-input-container">
                    <input
                      onChange={handleChange}
                      type="text"
                      name="title"
                      value={input.title}
                      className="task-input-title"
                    />
                    <textarea
                      onChange={handleChange}
                      name="description"
                      value={input.description}
                      className="task-input-description"
                    />
                    <label className="update-task-label">Alterar prazo</label>
                    <input
                      onChange={handleChange}
                      type="datetime-local"
                      name="deadlineDate"
                      value={input.deadlineDate}
                    />
                    <label className="update-task-label">Prioridade</label>
                    <select
                      name="priority"
                      onChange={handleChange}
                      className="task-input-priority"
                      value={input.priority}
                    >
                      <option value="LOW">Baixa</option>
                      <option value="AVERAGE">Média</option>
                      <option value="HIGH">Alta</option>
                    </select>
                  </div>
                  <button type="submit" className="task-form-submit-button">
                    Salvar
                  </button>
                  <button
                    type="button"
                    className="task-form-cancel-button"
                    onClick={toggleUpdateTask}
                  >
                    Cancelar
                  </button>
                </form>
              </div>
            ) : (
              <div className="task-toggle-update">
                <button
                  className="task-toggle-update-button"
                  onClick={toggleUpdateTask}
                >
                  <div className="task-detail-title-container">
                    <h3 className="task-detail-title">{title}</h3>
                    <p className="task-detail-description">{description}</p>
                  </div>
                </button>
              </div>
            )}

            <div className="task-detail-additional-info-div">
              <ConfirmDeleteButton onClick={handleRemove} />
              <UpdateTaskAdditionalInfo
                title={'Criado em'}
                info={capitalize(renderDate(createdAt))}
              />
              <UpdateTaskAdditionalInfo
                title={'Prazo'}
                info={capitalize(renderDate(deadlineDate))}
              />
              <UpdateTaskAdditionalInfo
                infoStyle={getPriorityStyle(priority)}
                title={'Prioridade'}
                info={capitalize(priority)}
              />
              <UpdateTaskAdditionalInfo
                title={'Status'}
                info={capitalize(status.replace('_', ' '))}
              />
            </div>
          </div>
        </Modal>
      )}
    </>
  );
}

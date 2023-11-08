import './dashboard.style.css';
import { Button, TaskCard } from '../../index';
import { Loading } from '../Loading/loading.component';
import { TbBeach } from 'react-icons/tb';
import { SORT_OPTIONS } from '../../../hook/useGetTasks/useGetTasks.hook';
import { HiSortAscending, HiSortDescending } from 'react-icons/hi';

export function Dashboard({
  tasks,
  filter,
  loading,
  toggleModal,
  updateInfo,
  sort,
  handleSort,
  handleSortOrder,
}) {
  function renderTasks() {
    if (tasks.length > 0) {
      return tasks?.map((task) => (
        <TaskCard
          key={task.id}
          filter={filter}
          data={task}
          updateInfo={updateInfo}
        />
      ));
    }
    return (
      <div className="dashboard-empty-warning-container">
        <TbBeach className="dashboard-empty-warning-icon" />
        <p className="dashboard-empty-warning">Nenhuma tarefa, parabéns!</p>
      </div>
    );
  }

  return (
    <section className="dashboard-section">
      <div className="dashboard-title-box">
        <span className="dashboard-title">{filter.name}</span>
        <div className="dashboard-side-buttons-container">
          <div className="dashboard-select-container">
            <select
              onChange={handleSort}
              value={sort.key}
              className="dashboard-select"
            >
              {Object.keys(SORT_OPTIONS).map((key) => {
                return (
                  <option key={key} value={key}>
                    {SORT_OPTIONS[key]}
                  </option>
                );
              })}
            </select>
            <button
              className="dashboard-order-button"
              onClick={handleSortOrder}
            >
              {sort.asc ? (
                <HiSortAscending className="dashboard-order-button-icon" />
              ) : (
                <HiSortDescending className="dashboard-order-button-icon" />
              )}
            </button>
          </div>
          <Button className="dashboard-title-button" onClick={toggleModal}>
            +
          </Button>
        </div>
      </div>
      <div className="dashboard-taskcards">
        {loading ? <Loading /> : renderTasks()}
      </div>
    </section>
  );
}

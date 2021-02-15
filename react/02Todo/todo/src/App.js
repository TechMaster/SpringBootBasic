//import logo from './logo.svg';
//import './App.css';
import { Component } from 'react';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: "Adam",
      todoItems: [
        { action: "Buy Flowers", done: false },
        { action: "Get Shoes", done: false },
        { action: "Collect Tickets", done: true },
        { action: "Call Joe", done: false }
      ],
      newItemText: "New Task"
    }
  }

  updateNewTextValue = (event) => {
    this.setState({ newItemText: event.target.value });
  }
  
  createNewTodo = () => {
    // Nếu không item nào trong todo list trùng với this.state.newItemText thì thêm task mới
    // Sau đó xoá trắng newItemText
    if (!this.state.todoItems.find(item => item.action === this.state.newItemText)) {
      this.setState(
        {
          // Ý nghĩa của 3 dots trong React
          //https://stackoverflow.com/questions/31048953/what-do-these-three-dots-in-react-do
          todoItems: [...this.state.todoItems, { action: this.state.newItemText, done: false }],
          newItemText: ""
        }
      );
    }
  }
  changeStateData = () => {
    this.setState({ userName: this.state.userName === "Adam" ? "Bob" : "Adam" })
  }

  toggleTodo = (todo) => 
    this.setState({ todoItems: 
      this.state.todoItems.map(item => item.action === todo.action ? { ...item, done: !item.done } : item)
    });

  todoTableRows = () => this.state.todoItems.map(item =>
    <tr key={ item.action }>    
      <td>{ item.action}</td>
      <td><input type="checkbox" checked={ item.done } onChange={ () => this.toggleTodo(item) } /></td>
    </tr>);

  render = () =>
    <div>
      <h4 className="bg-primary text-white text-center p-2">
        {this.state.userName}'s To Do List
        ({this.state.todoItems.filter(t => !t.done).length} items to do)
      </h4>
      <div className="container-fluid">
        <div className="my-1">
          <input className="form-control" value={this.state.newItemText}
            onChange={this.updateNewTextValue} />

          <button className="btn btn-primary mt-3"
            onClick={this.createNewTodo}>Add</button>
        </div>
        <table className="table table-striped table-bordered">
          <thead>
            <tr><th>Description</th><th>Done</th></tr>
          </thead>
          <tbody>{ this.todoTableRows() }</tbody>
        </table>
      </div>
    </div>
}

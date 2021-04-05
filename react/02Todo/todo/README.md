# Bổ xung Todo List

Phần này chủ yếu làm việc trên [App.js](src/App.js)

## 1. Thêm 2 thuộc tính ```todoItems``` và ```newItemText```

- ```todoItems``` là mảng danh sách các Task. Mỗi Task có 2 thuộc tính ```action``` kiểu string và ```done``` kiểu boolean
- ```newItemText``` lưu mô tả Task mới thêm

```js
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
}
```

## 2. Cập nhật 
```js
updateNewTextValue = (event) => {
  this.setState({ newItemText: event.target.value });
}
```
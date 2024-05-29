import React from 'react';
import axios from 'axios';

class CreatePost extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      title: '',
      content: '',
      boardId: null,
      error: null
    };
  }

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    });
  }

  handleSubmit = async (e) => {
    e.preventDefault();
    const { title, content } = this.state;

    try {
      const response = await axios.post('/api/boards', { title, content });
      this.setState({ boardId: response.data, error: null });
      console.log('生成された掲示板のID:', response.data);
    } catch (error) {
      this.setState({ error: 'ボードの作成に失敗しました' });
      console.error('エラー:', error);
    }
  }

  render() {
    const { title, content, error } = this.state;

    return (
        <div>
          <h2>掲示板作成</h2>
          {error && <div style={{ color: 'red' }}>{error}</div>}
          <form onSubmit={this.handleSubmit}>
            <div>
              <label>題名:</label>
              <input
                  type="text"
                  name="title"
                  value={title}
                  onChange={this.handleChange}
              />
            </div>
            <div>
              <label>内容:</label>
              <textarea
                  name="content"
                  value={content}
                  onChange={this.handleChange}
              />
            </div>
            <button type="submit">作成</button>
          </form>
        </div>
    );
  }
}

export default CreatePost;
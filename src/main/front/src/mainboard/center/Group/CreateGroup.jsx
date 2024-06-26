import React, { useState } from 'react';
import axios from 'axios';
import "./CreateGroup.css";

//need Group info =>
//Group name, Group Id, Group picture, Group description, number of Posts, number of member,

const CreateGroup = ({userId}) => {
  const [title, setTitle] = useState('');
  const [description, setContent] = useState('');
  const [boardId, setBoardId] = useState(null);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'title') {
      setTitle(value);
    } else if (name === 'description') {
      setContent(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = {
        userId : userId,
        description: description,
        title: title
      }
      console.log(data.userId + " " + data.description + " " + data.title);
      const response = await axios.post('http://localhost:8080/api/communities/create' , data  );
      setBoardId(response.data);
      setError(null);
      console.log('生成されたGroupのID:', response.data);
    } catch (error) {
      setError('ボードの作成に失敗しました');
      console.error('エラー:', error);
    }
  };

  return (
    <div className='CreatePost'>
      <h2 className='Title'>Group作成</h2>
      {error && <div style={{ color: 'red' }}>{error}</div>}
      <form onSubmit={handleSubmit} className='createPostForm'>
      <label>Group名:</label>
          <input
            type="text"
            name="title"
            value={title}
            onChange={handleChange}
            className='CPTextarea'
          />
        <div>
          <label>説明:</label>
          <textarea
            name="description"
            value={description}
            onChange={handleChange}
            className='CPTextarea'
          />
        </div>
        <button type="submit" className='CPButton'>作成</button>
      </form>
      </div>
  );
};

export default CreateGroup;



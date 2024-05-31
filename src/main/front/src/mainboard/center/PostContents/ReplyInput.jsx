import React, { useState} from 'react';

const ReplyInput = ({ onSubmit }) => {
    const [reply, setReply] = useState('');

    const handleChange = (e) => {
        setReply(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setReply(e.target.value);
        onSubmit(reply);
    };

    return (
        <form onSubmit={handleSubmit} className="reply-form">
            <textarea
                value={reply}
                onChange={handleChange}
                placeholder="Write your reply..."
            />
            <button type="submit">Submit</button>
        </form>
    );
};
export default ReplyInput;
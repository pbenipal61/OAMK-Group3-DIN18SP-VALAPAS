import mongoose from 'mongoose';

const schema = {
    name: {
        type: String,
        required: true,
    },

    aliases: [[String]] // [{language like fi, alias for that language}]
}

export default mongoose.model('Category', schema);
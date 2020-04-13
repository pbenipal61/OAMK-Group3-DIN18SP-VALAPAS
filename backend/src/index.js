import express from 'express';
import mongoose from 'mongoose';
import morgan from 'morgan';
import passport from 'passport';
import passportConfig from "./config/passport";

import routes from "./routes";

const app = express();
const port = 3000;

app.use(express.static(__dirname + 'uploads'));
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(passport.initialize());
passportConfig(passport);
mongoose
    .connect(
        `mongodb+srv://admin:i3f2YwP9iW@cluster0-990qj.mongodb.net/test?retryWrites=true&w=majority`,
        {
            useUnifiedTopology: true,
            useNewUrlParser: true,
            useCreateIndex: true,
            useFindAndModify: false
        }
    )
    .then((res) => {
      console.log('MongoDB connected...');
    })
    .catch((err) => {
      console.log('MongoDB failed to connect!', err);
    });


routes(app);


app.listen(port, () => console.log(`App listening on port ${port}!`))
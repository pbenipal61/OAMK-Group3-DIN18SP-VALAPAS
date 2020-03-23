import categories from "./categories";
import companies from "./companies";
import offerings from "./offerings";
import reservations from './reservations';
import users from './users';

const init = async (app) => {
    app.use("/categories", categories);
    app.use("/companies", companies);
    app.use("/offerings", offerings);
    app.use('/reservations', reservations);
    app.use('/users', users);
}

export default init;
import dotenv from 'dotenv';
import ppJwt from 'passport-jwt';
const Strategy = ppJwt.Strategy;
const ExtractJwt = ppJwt.ExtractJwt;
const secret = process.env.HASH_SECRET || "secret";
import mongoose from 'mongoose';
import User from "../models/User";

const options = {
    jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    secretOrKey: secret
};

export default (passport) => {
    passport.use(new Strategy(options, (payload, done) => {

        console.log('payload in middleware', payload);
        User.findById(payload.id)
            .then(user => {

                if(user){
                    return done(null, {id: user.name, email: user.email});
                }

                return done(null, false);
            })
            .catch(err => console.log(err));
    }));
};
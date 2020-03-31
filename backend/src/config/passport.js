import dotenv from 'dotenv';
dotenv.config();
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
        if(payload.user){
            return done(null, {user: payload.user});
        }

        return done(null, false);
    }));
};
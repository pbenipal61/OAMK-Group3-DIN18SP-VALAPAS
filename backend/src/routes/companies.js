import express from 'express';
import passport from 'passport';
import bcrypt from 'bcrypt';
import dotenv from 'dotenv';
dotenv.config();
import Company from "../models/Company";
const secret = process.env.HASH_SECRET || "secret";
const router = new express.Router();

router.post('/register', async (req, res, next) => {
    try{
        let input = req.body;
        if(!input.email || !input.password || !input.firstName){
            return res.status(500).json({
                status: "Failed",
                data: {
                    message: "Please provided necessary properties"
                }
            });
        }
        
        let user = await Company.findOne({email: req.email});
        if(user){
            return res.status(400).json({
                status: "Failed",
                data: {
                    message: "Email is registered already"
                }
            });
        }
        const salt = await bcrypt.genSaltSync(saltRounds);
        const hash = bcrypt.hashSync(input.password, salt);
        input.password = hash;
        user = await Company.create({...input});
        return res.status(202).json({
            status: "Success",
            data: {
                user
            }
        })
    }
    catch(err){
        return res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        });
    }

});

router.post("/login", async (req, res, next)  => {
    try{
        const input = req.body;
        if(!input.email || !input.password){
            return res.status(500).json({
                status: "Failed",
                data: {
                    message: "Email or password is missing"
                }
            });
        }

        const company = await Company.findOne({email: input.email});
        if(!company){
            return res.status(404).json({
                status: "Failed",
                data: {
                    message: "Email not registered"
                }
            });
        }

        const isPassMatch = await bcrypt.compareSync(input.password, company.password);
        if(!isPassMatch){
            return res.status(500).json({
                status: "Failed",
                data: {
                    message: "Incorrect password or email"
                }
            });
        };

        const jwtValidity = '30d';
        const jwtPayload = {
            company,
            validity: jwtValidity,
            timestamp : Date.now(),
        }

        const token = jwt.sign(jwtPayload, secret, {
            expiresIn: jwtValidity
        });

        res.status(200).json({
            status: "Success",
            token,
            tokenAsHeader: `Bearer ${token}`
        })

    }
    catch(err){
        return res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        });
    }
});

router.get('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        
        if(id){
            const company = await Company.findById(id);
            return res.status(200).json({status: "Success", data: {company}})
        }
        const companies = await Company.find({});
        return res.status(200).json({status: "Success", data: {companies}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }
});

router.put('/:id', passport.authenticate('jwt', {session: false}), async (req, res, next) => {
    try{
        const id = req.params.id;
        const input = req.body;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const company = await Company.findByIdAndUpdate(id, {...input}, { new: true});
        return res.status(200).json({status: "Success", data: {company}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }
});

router.delete('/:id', passport.authenticate('jwt', {session: false}), async (req, res, next) => {
    try{
        const id = req.params.id;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const company = await Company.findByIdAndDelete(id);
        return res.status(200).json({status: "Success", data: {company}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err: err.message
            }
        })
    }
});

export default router;
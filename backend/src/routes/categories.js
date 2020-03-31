import express from 'express';
import passport from 'passport';

import Category from "../models/Category";

const router = new express.Router();

router.post('/', passport.authenticate('jwt', {session: false}), async (req, res, next) => {
    try{
        const input = req.body;
        const category = await Category.create({...input});
        return res.status(202).json({
            status: "Success",
            data: {
                category
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
        })
    }

});

router.get('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        
        if(id){
            const category = await Category.findById(id);
            return res.status(200).json({status: "Success", data: {category}})
        }
        const categories = await Category.find({});
        return res.status(200).json({status: "Success", data: {categories}})
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
        const category = await Category.findByIdAndUpdate(id, {...input});
        return res.status(200).json({status: "Success", data: {category}})
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
        const category = await Category.findByIdAndDelete(id);
        return res.status(200).json({status: "Success", data: {category}})
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
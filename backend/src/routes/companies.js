import express from 'express';

import Company from "../models/Company";

const router = new express.Router();

router.post('/', async (req, res, next) => {
    try{
        const input = req.body;
        const company = await Company.create({...input});
        return res.status(202).json({
            status: "Success",
            data: {
                company
            }
        })
    }
    catch(err){
        return res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err
            }
        })
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
                err
            }
        })
    }
});

router.put('/:id', async (req, res, next) => {
    try{
        const id = req.params.id;
        const input = req.body;
        if(!id){
            return res.status(400).json({status: "Failed", data: {message: "Please provide an id"}})
        }
        const company = await Company.findByIdAndUpdate(id, {...input});
        return res.status(200).json({status: "Success", data: {company}})
    }
    catch(err){
        res.status(500).json({
            status: "Failed",
            data: {
                message: 'Internal server error',
                err
            }
        })
    }
});

router.delete('/:id', async (req, res, next) => {
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
                err
            }
        })
    }
});

export default router;